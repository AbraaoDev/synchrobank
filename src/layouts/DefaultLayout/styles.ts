import styled from 'styled-components'


export const LayoutContainer = styled.div`
    >main {
        width: 100%;
        max-width: 54rem;
        display: flex;
        flex-direction: column;
        align-items: center;
        
        justify-content: center;
        margin: 0 auto;


        @media(max-width: 54rem){
            padding: 0 1rem;
        }
    }
`