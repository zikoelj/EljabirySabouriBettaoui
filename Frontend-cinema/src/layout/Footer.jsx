import React from 'react'

export default function Footer() {
  return (
    <div>

        <footer class="py-5 bg-black">
            <div class="container px-5">
              <div className='row justify-content-evenly align-items-center'>
                <div className='col'>
                  <p className='m-1 lead text-center text-white'> Vision</p>
                  <p className='m-1 lead text-center text-white'>Prochainement</p>
                  <p className='m-1 lead text-center text-white'>Cinéma</p>
                </div>
                <div className='col'>
                  <p className='m-1 lead text-center text-white'>E-Ticket</p>
                  <p className='m-1 lead text-center text-white'>Transactions de retour</p>
                  <p className='m-1 lead text-center text-white'>-Accord de vente</p>
                </div>
              </div>
              <p class="mt-5 text-center text-white small">
                <strong>
                   Copyright &copy; cinema IDLD 2025
                    IDLD
                </strong> 
              </p>
            </div>
        </footer>

    </div>
  )
}
