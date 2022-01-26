import express from "express";
import { getAllPokemon, getPokemonByName } from '../controllers/pokemon.js'

const router = express.Router();

router.get('/:username', getAllPokemon);

router.get('/:name/:username', getPokemonByName);

export default router;