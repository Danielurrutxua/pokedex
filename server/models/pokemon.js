import mongoose from 'mongoose';

const pokemonSchema = mongoose.Schema({
    id: Number,
    name: String,
    photo: String,
});

const pokemon = mongoose.model('pokemon', pokemonSchema);

export default pokemon;