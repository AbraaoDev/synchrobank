import styled from "styled-components";

export const ButtonContainer = styled.button`
  .buttonAcc {
    height: 3rem;
    border-radius: 3rem;
    background-color: ${({ theme }) => theme.colors["color-bottom"]};
    border: 0;
    padding: 0 1.5rem;

    display: flex;
    align-items: center;
    justify-content: center;

    color: ${({ theme }) => theme.colors["gray-100"]};
    font-weight: 700;
  }

  border: none;
  background: none;


  svg {
    width: 20px;
    height: 20px;
  }

  svg:first-child {
    margin-right: 1rem;
  }

  transition: filter 0.2s ease-in-out;

  &:hover {
    filter: brightness(0.8);
  }
`;
