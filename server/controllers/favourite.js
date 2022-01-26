import Favourite from '../models/favourite.js';
import { getAllFavouritePokemonService, getAllPokemonService } from '../services/pokemonService.js'


export const  addRemoveFavourite = async (req, res, next) => {

    const request = req.body;
    const newFavourite = new Favourite(request);

    try {
    
        const loadedF = await Favourite.findOne({username: request.username, id_pokemon: request.id_pokemon})

        if(loadedF == null){
            await newFavourite.save();
        }else{
            await Favourite.deleteOne({username: request.username, id_pokemon: request.id_pokemon})
        }
       
    } catch (error) {
        res.status(404).json({error: error.message})
    }
}

export const isFavourite = async (req, res, next) => {

    const request = req.body
    const favourite = await Favourite.findOne({username:request['username'], id_pokemon: request['id_pokemon']})

    if(favourite == null) {
        res.satus(400).json("not favourite")
    }else{
        res.status(200).json("favourite")
    }
   
}

export const getAllFavourites = async (req, res, next) => {
    try {
        const allPokemon = await getAllFavouritePokemonService(200,0, req.params.username);
        res.status(200).json(allPokemon);
    } catch (error) {
        res.status(404).json({ message: error.message})
    }
}