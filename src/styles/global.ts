import { createGlobalStyle } from "styled-components";

export const GlobalStyles = createGlobalStyle`

* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: "Inter", sans-serif;

    ::-webkit-scrollbar {
      width: 0.45rem;
    }
    ::-webkit-scrollbar-track {
      background: ${({ theme }) => theme.colors["gray-500"]}
    }
    ::-webkit-scrollbar-thumb {
      background: ${({ theme }) => theme.colors["green"]};
      border-radius: 999px;
    }

    input::-webkit-outer-spin-button,
    input::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
}
}


@media(max-width: 1080px){
    html{
        font-size: 93.75%;
    }
}

@media(max-width: 720px){
    html{
        font-size: 87.5%;
    }
}

body {
    background: ${({ theme }) => theme.colors["gray-600"]};
    color: ${({ theme }) => theme.colors["gray-100"]};
    min-height: 100vh;
    -webkit-font-smoothing: antialiased;
}


button {
    cursor: pointer;
    border: none;
    background: none;
}

a{
    color: inherit;
    text-decoration: none;
}

input{
    outline: none;
    
}

input:focus{
    border: 1px solid ${({theme}) => theme.colors.green} ;
}





`;
