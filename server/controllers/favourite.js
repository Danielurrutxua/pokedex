import Favourite from '../models/favourite.js';

export const  addFavourite = async (req, res, next) => {

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

export const removeFavourite = async (req, res, next) => {

    const request = req.body
    const favourite = await Favourite.findOne({username:request['username'], id_pokemon: request['id_pokemon']})

    if(favourite == null) {
        res.satus(400).json("not favourite")
    }else{
        res.status(200).json("favourite")
    }   
}