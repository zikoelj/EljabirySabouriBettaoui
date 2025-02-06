import React, { useState } from 'react'
import { Formik, Form } from "formik";
import * as yup from "yup";
import { ActorService } from '../../services/actorService';
import { useEffect } from 'react';
import { CityService } from '../../services/cityService';
import Select from '../../utils/customFormItems/Select';
import { CategoryService } from '../../services/categoryService';
import { DirectorService } from '../../services/directorService';
import TextInputField from '../../utils/customFormItems/TextInputField';
import TextAreaField from '../../utils/customFormItems/TextAreaField';
import { MovieService } from '../../services/movieService';
import { useNavigate } from 'react-router-dom';
import CheckBoxFields from '../../utils/customFormItems/CheckBoxFields';
import { useSelector } from 'react-redux';

export default function AddMoviePage() {

    const userFromRedux = useSelector(state => state.user.payload)

    const navigate = useNavigate()

    const actorService = new ActorService();
    const cityService = new CityService();
    const categoryService = new CategoryService();
    const directorService = new DirectorService();
    const movieService = new MovieService();

    const [actors, setActors] = useState([])
    const [cities, setCities] = useState([])
    const [categories, setCategories] = useState([])
    const [directors, setDirectors] = useState([])

    useEffect(() => {
      actorService.getall().then(result => setActors(result.data))
      cityService.getall().then(result => setCities(result.data))
      categoryService.getall().then(result => setCategories(result.data))
      directorService.getall().then(result => setDirectors(result.data))
    }, [])
    
    const initValues = {
     
    }

    const validationSchema = yup.object({

   
    })


  return (
    <div>
        <div className='mt-5 p-5 container' style={{height: "150vh"}}>
            <h2 className='mt-4'>Ajouter un film</h2>
            <hr />

            <h5 className='my-4'>
                Complétez les informations sur le film et procédez à la sélection des acteurs du film.
            </h5>

            <Formik 
                initialValues={initValues}
                validationSchema={validationSchema}
                onSubmit={(values) => {
                    values.userAccessToken = userFromRedux.token; // Change here

                    if(values.directorId === undefined){
                        let director={
                            directorName: values.directorName,
                            token: userFromRedux.token
                        }
                        directorService.add(director).then(result => {
                            values.directorId = result.data.directorId
                            movieService.addMovie(values).then(result => 
                                {
                                    if(result.data != ""){
                                        navigate("/addMovie/" + result.data.movieId)
                                    }
                                });
                        })
                    } else {
                        movieService.addMovie(values).then(result => 
                            { 
                                if(result.data !== ""){
                                    navigate("/addMovie/" + result.data.movieId)
                                }
                            });
                    }
                }}>

                <Form>
                <div class="form-floating mb-3">
                    <TextInputField type="text" name='movieName' class="form-control" id="floatingInput" placeholder="Titre du film" />
                    <label for="floatingInput">Nom du film</label>
                </div>
                <div class="form-floating mb-3">
                    <TextAreaField name='description' class="form-control" id="floatingPassword" placeholder="Résumé" />
                    <label for="floatingPassword">Synopsis du film</label>
                </div>
                <div class="form-floating mb-3">
                    <TextInputField name='duration' type="number" class="form-control" id="duration" placeholder="Durée de l'accord" />
                    <label for="duration">Durée du film</label>
                </div>
                <div class="form-floating mb-3">
                    <TextInputField name='releaseDate' type="date" class="form-control" id="releaseDate" placeholder="Date de la vision" />
                    <label for="releaseDate">Date de la vision</label>
                </div>
                
                <div class="form-floating mb-3">
                    <TextInputField name='trailerUrl' type="text" class="form-control" id="trailerUrl" placeholder="Url de la bande-annonce" />
                    <label for="trailerUrl">Url de la bande-annonce</label>
                </div>

                <div class="form-floating mb-3">
                    <Select
                        id="categoryId"
                        className="form-select form-select-lg mb-3"
                        name="categoryId"
                        options={categories.map(category => (
                            {key: category?.categoryId, text:category?.categoryName, value: category?.categoryName}
                        ))}
                    />
                    <label for="categoryId">Catégorie</label>
                </div>  
                <div class="form-floating mb-3">
                    <Select
                        id="directorId"
                        className="form-select form-select-lg mb-3"
                        name="directorId"
                        options={directors.map(director => (
                            {key: director?.directorId, text: director?.directorName, value:director?.directorName}
                        ))}
                    />
                    
                    <label for="directorId">Directeur</label>
                </div>

                <p>Veuillez écrire si le directeur ne figure pas sur la liste ci-dessus.</p>
                <div class="form-floating mb-3">
                    
                    <TextInputField name='directorName' type="text" class="form-control" id="directorName" placeholder="Nom du directeur" />
                    <label for="directorName">Nom du directeur</label>
                </div>

                <div class="form-check mb-3 text-start">
                    <CheckBoxFields name="isInVision" class="form-check-input" type="checkbox" id="isInVision" />
                    <label class="form-check-label" for="isInVision">
                        Le film est-il en salle ?
                    </label>
                </div>
              

                <div class="input-group mb-3">
                    <input type="file" class="form-control" id="image" />
                </div> 

                    <div className="d-grid gap-2 my-4 col-6 mx-auto">
                      <input
                        type="submit"
                        value="En avant"
                        className="btn btn-block btn-primary"
                      />
                    </div>
                </Form>

              </Formik>
            
            
        </div>

        
    </div>
  )
}
