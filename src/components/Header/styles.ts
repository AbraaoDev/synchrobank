import styled from 'styled-components'

export const HeaderContainer = styled.header`
    height: 5rem;
    border-bottom: 1px solid ${({theme}) => theme.colors['gray-500']};
` 

export const HeaderContent = styled.div`
    max-width: 1120px;
    height: 5rem;
    margin: 0 auto;
    padding: 0 2rem;
    
    display: flex;
    align-items: center;

    nav{
        margin-left: 9.7rem;
        height: 5rem;

        a{
            display: inline-block;
            position: relative;

            padding: 0 .5rem;
            height: 5rem;
            line-height: 5rem;
            color: ${({theme}) => theme.colors['gray-200']};

            transition: color .2s ease-in-out;
            cursor: pointer;

            &:hover{
                color: ${({theme}) => theme.colors['gray-100']};
            }

            & + a{
                margin-left: 2rem;
            }

            &.active{
                color: ${({theme}) => theme.colors['gray-100']};
                font-weight: 700;
            }

            &.active::after{
                content: '';
                height: 3px;
                border: 3px 3px 0 0;
                border-radius: 2px;

                width: 100%;
                position: absolute;
                bottom: 1px;
                left: 0;
                background: ${({theme}) => theme.colors.green};
            }


        }
    }
    button{
        margin-left: auto;
    }
`