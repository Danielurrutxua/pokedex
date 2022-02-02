import mongoose from 'mongoose';

const userSchema = mongoose.Schema({
    username:{
        type: String,
        required: true,
        min: 6,
        max: 250
    },
    username:{ 
        type: String,
        required: true,
        max: 250
    },
    password:{
        type: String,
        required: true,
        min: 1024
    }
});

export default mongoose.model('User', userSchema);
