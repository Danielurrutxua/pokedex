import express from "express";
import bodyParser from "body-parser";
import cors from "cors";
import pokemonRoutes from './routes/pokemon.js';
import authRoutes from './routes/auth.js';
import favRoutes from './routes/favourite.js';
import morgan from "morgan"
import swaggerUi from'swagger-ui-express';
import swaggerDocument from './swagger.json';
import connectDb from './database.js';

const app = express();

app.use(bodyParser.json({limit: "20mb", extended:true}));
app.use(bodyParser.urlencoded({limit: "20mb", extended:true}));
app.use(cors());
app.use('/pokemon', pokemonRoutes);
app.use('/user', authRoutes);
app.use('/favourite', favRoutes);
app.use(morgan('dev'));
app.use(
    '/api-docs',
    swaggerUi.serve, 
    swaggerUi.setup(swaggerDocument)
  );

connectDb();

const PORT = process.env.PORT || 5000;

 app.listen(PORT, () =>
console.log('Connection is established and running o port: ', PORT));
 

