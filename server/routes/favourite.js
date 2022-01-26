import express from "express";
import { addRemoveFavourite, isFavourite, getAllFavourites } from '../controllers/favourite.js'

const router = express.Router();

router.post('/add', addRemoveFavourite);

router.get('/', isFavourite);

router.get('/all/:username', getAllFavourites);

export default router;