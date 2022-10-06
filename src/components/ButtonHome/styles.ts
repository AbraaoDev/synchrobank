import styled from "styled-components";

export const ButtonHomeContainer = styled.button`
  .buttonHo {
    height: 3rem;
    border-radius: 3rem;
    background-color: ${({ theme }) => theme.colors["green"]};
    border: 0;
    padding: 0 1.5rem;

    display: flex;
    align-items: center;
    justify-content: center;

    color: ${({ theme }) => theme.colors["gray-700"]};
    font-weight: 700;
    transition: filter .2s ease-in-out;
    &:hover {
    filter: brightness(0.8);
  }
  }

  svg {
    width: 20px;
    height: 20px;
  }

  svg:first-child {
    margin-right: 1rem;
  }

 
`;
