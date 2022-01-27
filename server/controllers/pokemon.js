import { getAllPokemonService } from '../services/pokemon/list.js'
import { getPokemonByNameService } from '../services/pokemon/detail.js'


export const getAllPokemon = async (req, res)=> {
    try {
        const username = req.body.username || null;
        const allPokemon = await getAllPokemonService(username);
        res.status(200).json(allPokemon);
    } catch (error) {
        res.status(404).json({ message: error.message})
    }
}

export const getPokemonById = async (req, res) => {
    try {
        const username = req.body.username || null;
        const pokemon = await getPokemonByNameService(req.params.id, username);
        res.status(200).json(pokemon);
    } catch (error) {
        res.status(400).json({ message: error.message})
    }
}