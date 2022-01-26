import axios from "axios"
import Favourite from '../models/favourite.js';
const apiUrl = "https://pokeapi.co/api/v2/";

 export const getAllPokemonService = async (limit = 20, offset = 0, _username) => {
    try {
      
      const results = await axios.get(
        apiUrl + `pokemon/?limit=${limit}&offset=${offset}`
      );
      const ids = await Favourite.find({username: 'danielu227'})

      results.data.results = addFavourites(ids, results.data.results)
      return results.data;
    } catch (err) {
      if (err) throw err;
    }
  };

  export const getAllFavouritePokemonService = async (limit = 20, offset = 0, _username) => {
    try {
      
      const results = await axios.get(
        apiUrl + `pokemon/?limit=${limit}&offset=${offset}`
      );
      const ids = await Favourite.find({username: 'danielu227'})

      results.data.results = addFavourites(ids, results.data.results)
      results.data.results = onlyFavourites(results.data.results)

      return results.data;
    } catch (err) {
      if (err) throw err;
    }
  };

  function onlyFavourites(results){
    const results1 = [];
    
      for(var i = 0;i<results.length;i++){
        if(results[i].favourite == true){
          results1.push(results[i]);
        }
      }
      return results1;
  }

  function addFavourites(ids, results) {
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

  export const getPokemonByNameService= async (_name, _username) => {
    try {
      const { data } = await axios.get(apiUrl + `pokemon/${_name}`);
      const { abilities, sprites, name, id, types, height, weight } = data;
      const results = { abilities, photos: sprites, name, id, types, height, weight };

      const isFavourite = await Favourite.findOne({username: _username, id_pokemon: results.id})
      if(isFavourite != null){
        results.favourite = true;
      }else{
        results.favourite = false;
      }
      return results;
    } catch (err) {
      if (err) throw err;
    }
  };

const pokemonInformations = async (pokemons) => {
  try {
    const iteratePokemons = pokemons.results.map(async (pokemon) => {
      let { data } = await axios.get(pokemon.url);
      let { id, name, sprites } = data;
      let results = {
        id: id,
        name: name,
        photo: sprites.front_default,
      };
      return results;
    });
    const datas = await Promise.all(iteratePokemons);
    const { count, next, previous } = pokemons;
    return Object.assign({ count, next, previous }, datas);
  } catch (err) {
    if (err) throw err;
  }
};


