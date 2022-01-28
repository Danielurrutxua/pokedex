import axios from "axios"
const apiUrl = "https://pokeapi.co/api/v2/";

export const getAllPokemonService = async (_username) => {
  try {
    const results = await axios.get(apiUrl + `pokemon/?limit=1118&offset=0`);

    return formatResults(results.data.results);
  } catch (err) {
    if (err) throw err;
  }
};

const formatResults = async (results) => {
  const typesNamesMap = await getTypesNamesMap();

  const iteratePokemons = results.map((pokemon) => {
    const id = parseInt(pokemon.url.split('/')[6]);
    let results = {
      name: pokemon.name,
      id: id,
      favourite: false,
      types: getKeys(typesNamesMap, id)
    };

    return results;
  });

  return iteratePokemons;
}

const getTypesNamesMap = async () => {
  const typeNamesMap = new Map();
  const data = await axios.get(apiUrl + 'type')
  const results = data.data.results;

  for (var i = 0; i < results.length; i++) {
    const typeData = await axios.get(results[i].url);
    const pokemonIds = getPokemonIds(typeData.data.pokemon);
    typeNamesMap.set(results[i].name, pokemonIds);
  }
  return typeNamesMap;

}

function getPokemonIds(pokemons) {
  const ids = [];
  for (var i = 0; i < pokemons.length; i++) {
    ids.push(pokemons[i].pokemon.url.split('/')[6]);
  }
  return ids;
}

function getKeys(map, id) {
  const keys = [];
  map.forEach((value, key) => {

    if (value.includes(id.toString())) {
      keys.push(key)
    }
  });

  
  return keys;
}