import express from "express";
import { getAllPokemon, getPokemonById } from '../controllers/pokemon.js'

const router = express.Router();

router.get('/', getAllPokemon);

router.get('/:id', getPokemonById);

export default router;