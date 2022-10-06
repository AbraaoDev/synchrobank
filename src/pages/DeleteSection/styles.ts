import styled from "styled-components";

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
