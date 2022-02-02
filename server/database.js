import mongoose from 'mongoose';
import dotenv from 'dotenv';

dotenv.config();

export default function connectDb() {
    mongoose.connect(process.env.DB_CONNECT,
    () => console.log('connected to db!'))
}
