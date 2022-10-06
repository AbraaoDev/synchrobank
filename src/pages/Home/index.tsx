import heroImg from "../../assets/heroImg.svg";
import heroTest from "../../assets/heroTest.svg";
import { ButtonHome } from "../../components/ButtonHome";
import { Hero, HomeContainer } from "./styles";
import  Aos  from 'aos'
import 'aos/dist/aos.css'
import { useEffect } from "react";

export function Home() {

  useEffect(() => {
    Aos.init({ duration: 1500})
  }, [])

  return (
    <>
      <HomeContainer data-aos="fade-up">
        <Hero>
          <span>🚀 Olá, bem-vindo</span>
          <h1>
            No <span>Synchro BANK</span> seu dinheiro fica seguro{" "}
          </h1>
          <p>Além de fazer seu dinheiro render, você pode usá-lo <span>na hora</span>.</p>
          <ButtonHome />
        </Hero>

        <img src={heroTest} alt="Bank" />
      </HomeContainer>
    </>
  );
}
