import express from "express";
import bodyParser from "body-parser";
import mongoose from "mongoose";
import cors from "cors";
import pokemonRoutes from './routes/pokemon.js';
import userRoutes from './routes/user.js';
import favRoutes from './routes/favourite.js';
import morgan from "morgan"

import swaggerUi from'swagger-ui-express';
import swaggerDocument from './swagger.json';

const app = express();

app.use(bodyParser.json({limit: "20mb", extended:true}));
app.use(bodyParser.urlencoded({limit: "20mb", extended:true}));
app.use(cors());
app.use('/pokemon', pokemonRoutes);
app.use('/user', userRoutes);
app.use('/favourite', favRoutes);
app.use(morgan('dev'));

app.use(
    '/api-docs',
    swaggerUi.serve, 
    swaggerUi.setup(swaggerDocument)
  );


const CONNECTION_URL = "mongodb+srv://danielu227:FontBella2@cluster0.jbj8g.mongodb.net/pokedex?retryWrites=true&w=majority";


const PORT = process.env.PORT || 5000;

mongoose.connect(CONNECTION_URL, {
    useNewUrlParser:true, useUnifiedTopology:true
}).then(() => app.listen(PORT, () =>
console.log('Connection is established and running o port: ', PORT)
)).catch((err) => console.log(err.message));
 

