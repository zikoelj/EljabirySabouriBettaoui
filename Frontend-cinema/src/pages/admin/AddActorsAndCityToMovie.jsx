import { Form, Formik } from 'formik';
import React, { useState } from 'react'
import { useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { ActorService } from '../../services/actorService';
import { CityService } from '../../services/cityService';
import Select from '../../utils/customFormItems/Select'
import TextInputField from '../../utils/customFormItems/TextInputField'
import * as yup from "yup";
import { MovieImageService } from '../../services/movieImageService';
import { useSelector } from 'react-redux';

export default function AddActorsAndCityToMovie() {

    let {movieId} = useParams();
    const navigate = useNavigate()

    const userFromRedux = useSelector(state => state.user.payload)

    const cityService = new CityService();
    const actorService = new ActorService();
    const movieImageService = new MovieImageService();

    const [cities, setCities] = useState([])
    const [actors, setActors] = useState([])

    useEffect(() => {
        cityService.getall().then(result => {
            let arr = [];
            result.data.forEach(element => {
                if(!arr.includes(element?.cityName)){
                    arr.push(element?.cityName)
                }
            });
            setCities(arr)
        })
        actorService.getall().then(result => {
            let arr = [];
            result.data.forEach(element => {
                if(!arr.includes(element?.actorName)){
                    arr.push(element?.actorName)
                }
            });
            setActors(arr)
        })
      }, [])

    const initValues = {
     
    }

    const validationSchema = yup.object({

   
    })

  return (
    <div>
        <div className='mt-5 p-5 container' style={{height: "100vh"}}>
            <h2 className='mt-4'>Ajouter un film</h2>
            <hr />

            <h5 className='my-4'>
                Ajoutez les informations sur l'acteur et la ville du film que vous avez ajouté.
            </h5>

            <Formik 
                initialValues={initValues}
                validationSchema={validationSchema}
                onSubmit={(values) => {
                    let actorNameList;

                    if(!values.actorname && values.actorName.trim() != "") {
                        if(values.actors !== undefined){

                            actorNameList = [...values.actors, ...values.actorName.split(", ")] 
                        }else {

                            actorNameList = [...values.actorName.split(", ")] 
                        }
                    } else {
                        actorNameList = [...values.actors] 
                    }
                    let actorDto = {
                        movieId: movieId,
                        actorNameList: actorNameList,
                        token: userFromRedux.token
                    }
                    let cityDto = {
                        movieId: movieId,
                        cityNameList: values.cities,
                        token: userFromRedux.token
                    }
                    let movieImageDto = {
                        movieId: movieId,
                        imageUrl: values.imageUrl,
                        token: userFromRedux.token
                    }
                    
                    actorService.addActor(actorDto);
                    movieImageService.addMovieImage(movieImageDto);
                    cityService.addCity(cityDto).then(result => navigate("/addMovie"));
                }}>

                <Form>
                    <div class="mb-3">
                        <Select
                            class="form-select form-select-lg mb-3"
                            name="actors"
                            multiple
                            size={3}
                            options={actors.map(actor => (
                                {key: actor, text:actor, value: actor}
                            ))}
                        />
                    </div>
                    <p>S'il ne figure pas dans la liste, veuillez l'indiquer en le séparant par une virgule.</p>
                    <div class="form-floating mb-3">
                        <TextInputField type="text" name='actorName' class="form-control" id="floatingInput" placeholder="Nom de l'acteur" />
                        <label for="floatingInput">Nom de l'acteur</label>
                    </div>

                    <div class="form-floating mb-3">
                        <TextInputField name='imageUrl' type="text" class="form-control" id="imageUrl" placeholder="Url de l'image de la bannière" />
                        <label for="imageUrl">Url de l'image de la bannière</label>
                    </div>

                     <div class="mb-3">
                        <Select
                            class="form-select form-select-lg mb-3"
                            name="cities"
                            multiple
                            size={3}
                            options= {cities.map(city => (
                                {key: city, text:city, value: city}
                            ))}
                            placeholder="Ville"
                        />
                    </div>

                    <div className="d-grid gap-2 my-4 col-6 mx-auto">
                      <input
                        type="submit"
                        value="Ajouter"
                        className="btn btn-block btn-primary"
                      />
                    </div>
                </Form>
            </Formik>
        </div>
    </div>
  )
}
