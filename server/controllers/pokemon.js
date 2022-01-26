import { getAllPokemonService, getPokemonByNameService } from '../services/pokemonService.js'


export const getAllPokemon = async (req, res)=> {
    try {
        const allPokemon = await getAllPokemonService(200,0, req.params.username);
        res.status(200).json(allPokemon);
    } catch (error) {
        res.status(404).json({ message: error.message})
    }
}

export const getPokemonByName = async (req, res) => {
    try {
        const pokemon = await getPokemonByNameService(req.params.name, req.params.username);
        res.status(200).json(pokemon);
    } catch (error) {
        res.status(404).json({ message: error.message})
    }
}