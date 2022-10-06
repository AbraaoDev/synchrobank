import styled from "styled-components";

export const Container = styled.main`
    width: 1120px;
    padding: 0 2rem;
    height: calc(100vh - 10rem);
    display: flex;
    align-items: center;
    justify-content: space-between;

    


`


export const Content = styled.section`
    h1{
        font-size: 2.5rem;
        line-height: 2.5rem;
        font-weight: 900;
        margin-top: 2rem;
        margin-bottom: 2rem;
        color: ${({theme}) => theme.colors["gray-100"]};
    }


    div{

        display: flex;
        align-items: center;
        gap: 0.625rem;



        span{
            color: ${({theme}) => theme.colors["gray-300"]};
            font-size: 0.875rem;

        }
    }


`


export const FormSynchro = styled.form`

    background: ${({theme}) => theme.colors["gray-500"]};
    padding: 2rem;
    width: 50rem;
    border-radius: 0.5rem;
    background: linear-gradient(90deg, #262626, #262626) padding-box, linear-gradient(180deg, #00B37E 0%, rgba(225, 225, 230, 0) 100%) border-box;
    box-shadow: 0px 2px 28px rgba(0,0,0,0.2);
    border: 2px solid transparent;
    
    h1{
        color: ${({theme}) => theme.colors["green"]};
        font-size: 1.5rem;
        margin-bottom: 2rem;
    }

    input{
        width: 100%;
        height: 3rem;
        background: ${({theme}) => theme.colors["gray-600"]};
        border: 1px solid ${({theme}) => theme.colors["gray-400"]};
        border-radius: 0.5rem;
        padding: 1rem;
        margin-bottom: 1rem;
        color: ${({theme}) => theme.colors["gray-100"]};





    }

    div{
        display: flex;
        align-items: center;
        justify-content: center;
        gap: 0.5rem;
    }

    button{
        width: 50%;
        height: 3rem;
        background: ${({theme}) => theme.colors.green};
        border-radius: 0.5rem;
        color: ${({theme}) => theme.colors["gray-100"]};
        font-weight: 700;
        transition: filter .2s ease-in-out;
        display: flex;
        align-items: center;
        justify-content: center;
        margin: 0 0 0 auto;

        &:hover{
            filter: brightness(.8);
        }
    }


`