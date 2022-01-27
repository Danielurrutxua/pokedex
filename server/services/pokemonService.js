import axios from "axios"
import Favourite from '../models/favourite.js';
const apiUrl = "https://pokeapi.co/api/v2/";

 export const getAllPokemonService = async ( _username) => {
    try {
      const results = await axios.get(apiUrl + `pokemon/?limit=1118&offset=0`);
      if(_username != null){
        const ids = await Favourite.find({username: _username})
        results.data.results = addFavouritesValues(ids, results.data.results)
      }
      return results.data;
    } catch (err) {
      if (err) throw err;
    }
  };

  function addFavouritesValues(ids, results) {
    const ids1 = []
    const results1 = results
    for(var i = 0; i<ids.length;i++){
        ids1.push(ids[i]['id_pokemon'])
    }

    for(var i in ids1) {
        results1[ids1[i]-1]['favourite'] = true
    }
  
    return results1;
  }

  export const getPokemonByNameService= async (_id, _username) => {
    try {
      const { data } = await axios.get(apiUrl + `pokemon/${_id}`);
      const { abilities, sprites, name, id, types, height, weight } = data;
      const results = { abilities, photos: sprites, name, id, types, height, weight };
      if(_username != null){
        results.favourite = isFavourite(_username, _id)
      }
      return results;
    } catch (err) {
      if (err) throw err;
    }
  };

  async function isFavourite(username, pokemon_id){
    const result = await Favourite.findOne({username: username, id_pokemon: pokemon_id})
    if(result == null){
      return false;
    }else{
      return true;
    }
  }




