import styled, { ThemeConsumer } from "styled-components";

export const SearchForm = styled.form`
  height: 3.375rem;
  margin-top: 3.375rem;
  width: 100%;
  max-width: 46rem;
  display: flex;

  gap: 0.5rem;
  padding: 0 1rem;

  > input {
    height: 100%;
    flex: 1;
    color: ${({ theme }) => theme.colors["gray-100"]};
    background: ${({ theme }) => theme.colors["gray-500"]};
    border: 1px solid ${({ theme }) => theme.colors["gray-700"]};
    border-radius: 0.5rem;
    padding: 0 1rem;
    font-size: 1rem;
  }
`;

export const SearchButton = styled.button`
  .buttonSearch {
    height: 100%;
    border-radius: 0.5rem;
    background-color: ${({ theme }) => theme.colors["color-bottom"]};
    border: 0;
    padding: 0 1rem;

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
    margin-left: 1rem;
  }

  transition: filter 0.2s ease-in-out;

  &:hover {
    filter: brightness(0.8);
  }
`;

export const Counter = styled.section`
  width: 100%;
  max-width: 46rem;
  margin: 0 auto;
  margin-top: 3rem;
  padding: 0 1rem;

  > header {
    display: flex;
    align-items: center;
    justify-content: center;
    margin-bottom: 1.5rem;

    > div {
      display: flex;
      align-items: center;
      gap: 0.8rem;

      p {
        color: ${({ theme }) => theme.colors.green};
        font-size: 0.875rem;
        font-weight: 700;
      }

      span{
        background: ${({theme}) => theme.colors["gray-400"]};
        color: ${({theme}) => theme.colors["gray-200"]};
        padding: 3px 9px;
        border-radius: 999px;
        font-size: 0.75rem;
        font-weight: 700;
      }
    }
  }
`;

export const WrapperAccount = styled.div`
  display: flex;
  flex-direction: column;
  gap: 0.75rem;
`


export const EmptyAccount = styled.section`
  margin-top: 2rem;
  display: flex;
  align-items: center;  
  justify-content: center;
  flex-direction: column;
  border-top: 1px solid ${({theme}) => theme.colors["gray-400"]};
  border-radius: 0.5rem;
  gap: 1rem;
  color: ${({theme}) => theme.colors["gray-300"]};
  text-align: center;

  svg{
    margin-top: 3rem;
    opacity: 0.3rem;
  }

  p{
    font-weight: 700;
  }

`

