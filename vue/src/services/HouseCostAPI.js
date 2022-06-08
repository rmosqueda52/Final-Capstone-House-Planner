import axios from "axios";
export default {


    getCostOfHouse(house) {

        const options = {
            method: 'GET',
            url: 'https://us-real-estate.p.rapidapi.com/v2/for-sale',
            params: {
                offset: '0',
                limit: '1',
                state_code: house.state_abbreviation,
                city: house.houseParamsCity,
                sort: 'newest',
                beds_min: house.bedrooms,
                baths_min: house.bathrooms,
                property_type: 'single_family',
                home_size_min: house.size,
                stories: house.stories
            },
            headers: {
                'X-RapidAPI-Host': 'us-real-estate.p.rapidapi.com',
                'X-RapidAPI-Key': 'e4a60f80edmsh8a02bc981672585p1f5556jsn1bde7ae8d189'
            }
        };

        axios.request(options).then(function (response) {
            console.log(response.data);
        }).catch(function (error) {
            console.error(error);
        })

    }
}