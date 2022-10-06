import styled, {keyframes} from 'styled-components'


const toFloat = keyframes`
0%, 100%{
    transform: translateY(0px);
}
50%{
    transform: translateY(-10px);
}
`


export const HomeContainer = styled.main`
    width: 1120px;
    padding: 0 2rem;
    height: calc(100vh - 8rem);
    display: flex;
    align-items: center;
    justify-content: space-between;

    img{
        animation: ${toFloat} 3s ease-in-out infinite;
        position: absolute;
        z-index: -1;
        display: flex;
        padding: 0 0 0 30rem;
        width: 68rem;
    }
 

`
export const Hero = styled.section`
    max-width: 600px;

    >span{
        font-size: 1.5rem;
        font-weight: 700;

    }

    h1{
        font-size: 2.5rem;
        line-height: 2.5rem;
        font-weight: 900;
        margin-top: 2rem;

        span{
            color: ${({ theme}) => theme.colors['purple']};

        }
    }

    p {
        font-size: 1rem;
        line-height: 2rem;
        margin-top: 1.5rem;
        color: ${({theme}) => theme.colors['gray-300']};

        span{
            color: ${({theme}) => theme.colors['green']};
            font-weight: 700;
        }
    }

    button{
        margin-top: 2.5rem;
    }

    img{
        
    }
`