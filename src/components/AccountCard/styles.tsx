import styled from "styled-components";

export const Container = styled.div`

  width: 100%;
  background: ${({theme}) => theme.colors["gray-500"]};
  border: 1px solid ${({theme}) => theme.colors["gray-400"]};
  padding: 1rem;
  border-radius: 0.5rem;
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 0.75rem;

  > span{
    border: 2px solid ${({ theme }) => theme.colors["green"]};
    color: ${({ theme }) => theme.colors["gray-100"]};
    padding: 3px 8px;
    border-radius: 999px;
    font-size: 0.75rem;
    font-weight: 700;
  }

  .name{
    color: ${({theme}) => theme.colors["purple"]};
    font-weight: 700;
  }

  p{
    font-size: 0.875rem;
    line-height: 1.18rem;
    color: ${({theme}) => theme.colors["gray-100"]};

  }

  


`;

export const CounterCard = styled.div`
  display: flex;
  align-items: center;
  gap: 0.8rem;

  p {
    color: ${({ theme }) => theme.colors.green};
    font-size: 0.875rem;
    font-weight: 700;
  }

  span {
    background: ${({ theme }) => theme.colors["gray-400"]};
    color: ${({ theme }) => theme.colors["gray-200"]};
    padding: 3px 9px;
    border-radius: 999px;
    font-size: 0.75rem;
    font-weight: 700;
  }
`;
