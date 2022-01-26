import express from "express";
import { getAllPokemon, getPokemonByName } from '../controllers/pokemon.js'

const router = express.Router();

router.get('/', getAllPokemon);

router.get('/:name', getPokemonByName);

export default router;