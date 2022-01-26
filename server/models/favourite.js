import mongoose from 'mongoose';

const favSchema = mongoose.Schema({
    username: String,
    id_pokemon: Number,
});

export default mongoose.model('Favourite', favSchema);