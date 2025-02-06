import React, { useEffect, useState } from 'react'
import { Swiper, SwiperSlide } from "swiper/react";
import "swiper/css";
import "swiper/css/pagination";
import { Pagination } from "swiper";
import { MovieService } from '../services/movieService';
import { useNavigate } from 'react-router-dom';

export default function MainPage() {
 
    const movieService = new MovieService();

    const navigate = useNavigate();
    const [movies, setMovies] = useState([]);

    async function getMovies(isComingSoon) {
        if (isComingSoon) {
            await movieService.getAllComingSoonMovies().then(result => setMovies(result.data))
        }else {
            await movieService.getAllDisplayingMovies().then(result => setMovies(result.data))
        }
    }

    useEffect(() => {
      getMovies(false);
    }, [])
    

  return (
    <div>

    <body id="page-top">
    <section>
        <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="false">
            <div class="carousel-indicators">
                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active"
                        aria-current="true" aria-label="Slide 1"></button>
                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1"
                        aria-label="Slide 2"></button>
                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2"
                        aria-label="Slide 3"></button>
                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="3"
                        aria-label="Slide 4"></button>

            </div>
            <div className="carousel-inner">
                <div className="carousel-item active">
                    <header class="masthead text-center text-white">
                        <div className="masthead-content">
                            <div className="container px-5">
                                <h1 class="masthead-heading mb-0">Cinema IDLD</h1>
                                <h2 class="masthead-subheading mb-0">
                                    Profitez du film avec Cinema IDLD
                                </h2>
                                <h2 class="mt-3">
                                    Les films les plus récents dans les salles de cinéma Cinema IDLD </h2>
                                <a class="btn btn-primary btn-xl rounded-pill mt-5" href="#scroll">Films</a>
                            </div>
                        </div>

                    </header>

                </div>
                {/* Second slide */}
                <div className="johnwick-bg carousel-item">

</div>
                <div className="matrix-bg carousel-item">

                </div>

                {/* Third slide */}
                <div className="boyka-bg carousel-item">

                </div>
                
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Précédent</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Suivant</span>
        </button>
        </div>
    </section>


    {/* Section - 2 Navs & Tabs */}

    <section className='py-5'>
        <div className='d-flex justify-content-center'>
            <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
                <li class="nav-item" role="presentation">
                    <button class="nav-link active" id="pills-home-tab" data-bs-toggle="pill" 
                        data-bs-target="#pills-home" type="button"
                        role="tab" aria-controls="pills-home" aria-selected="true"
                        onClick={() => {
                            getMovies(false)
                        }}>Vision</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="pills-profile-tab" data-bs-toggle="pill"
                    data-bs-target="#pills-profile"
                    type="button" role="tab" aria-controls="pills-profile" aria-selected="false"
                    onClick={() => {
                        getMovies(true)
                    }}>Prochainement</button>
                </li>
            </ul>
        </div>
    </section>

    {/* Section - 3 Movie Carrousel */}

    <section className='mb-5'>
        <Swiper
            slidesPerView={5}
            spaceBetween={0}
            pagination={{
                clickable: true,
            }}
            modules={[Pagination]}
            className="mySwiper movie-slider"
        >
            {movies.map(movie => (
                <SwiperSlide key={movie.movieId}>
                    <div className='slider-item' onClick={()=> navigate("/movie/" + movie.movieId)}>
                        <div className='slider-item-caption d-flex align-items-end justify-content-center h-100 w-100'>
                            <div class="d-flex align-items-center flex-column mb-3" style={{height: "20rem"}}>
                                <div class="mb-auto pt-5 text-white"><h3> {movie.movieName} </h3></div>
                                <div class="p-2 d-grid gap-2">
                                    <a class="slider-button btn btn-light btn-md rounded d-none d-sm-block"
                                        onClick={()=> navigate("/movie/" + movie.movieId)}>
                                        <strong>Laisser un commentaire </strong>
                                    </a>
                                    <a class="slider-button btn btn-light btn-md rounded d-none d-sm-block"
                                        onClick={()=> navigate("/movie/" + movie.movieId)}>
                                        <strong> Acheter un billet </strong>
                                    </a>
                                </div>
                            
                            </div>
                        </div>
                        <img src={movie.movieImageUrl}
                            class="img-fluid mx-2" alt="..." style={{width: "300px",height: "452px"}}/>
                    </div>
                </SwiperSlide>
            ))}
            

        </Swiper>

   
    </section>

  
        
    </body>
    </div>
  )
}
