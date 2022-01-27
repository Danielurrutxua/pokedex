import axios from "axios"
import Favourite from '../../models/favourite.js';
const apiUrl = "https://pokeapi.co/api/v2/";

export const getPokemonByNameService = async (_id, _username) => {
    try {
        const { data } = await axios.get(apiUrl + `pokemon/${_id}`);
        const { abilities, name, id, types, height, weight, stats } = data;
        const results = { abilities: formatAbilities(abilities), name, id, types: formatTypes(types), height, weight, stats: formatStats(stats), favourite: false };

        return results;
    } catch (err) {
        if (err) throw err;
    }
};

function formatAbilities(abilities) {
    var formattedAbilities = '[';

    for (var i = 0; i < abilities.length; i++) {
        const name = '"' + abilities[i].ability.name + '"'
        if (i == abilities.length - 1) {
            formattedAbilities += name
        } else {
            formattedAbilities += name + ','
        }
    }

    return JSON.parse(formattedAbilities += ']');
}

function formatTypes(types) {
    var formattedTypes = '[';

    for (var i = 0; i < types.length; i++) {
        const name = '"' + types[i].type.name + '"'
        if (i == types.length - 1) {
            formattedTypes += name
        } else {
            formattedTypes += name + ','
        }
    }

    return JSON.parse(formattedTypes += ']');
}

function formatStats(stats) {
    var formattedStats = '{';

    for (var i = 0; i < stats.length; i++) {
        const name = stats[i].stat.name
        const value = stats[i].base_stat
        if (i == stats.length - 1) {
            formattedStats += '"' + name + '":' + value + ''
        } else {
            formattedStats += '"' + name + '":' + value + ','
        }
    }
    return JSON.parse(formattedStats += '}');
}




