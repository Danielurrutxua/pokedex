import express from "express";
import { addFavourite, removeFavourite } from '../controllers/favourite.js'

const router = express.Router();

router.post('/add', addFavourite);

router.get('/remove', removeFavourite);

export default router;