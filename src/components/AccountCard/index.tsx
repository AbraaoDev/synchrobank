import axios from "axios";
import { useEffect, useState } from "react";
import { Container, CounterCard } from "./styles";

interface CardProps {
  id: number;
  digitCode: string;
  flag: string;
  name: string;
  number: string;
  typeCard: {
    id: number;
    name: string;
  };
}

interface AccountCardProps {
  id: number;
  agency: string;
  account: string;
  digit: string;
  card: CardProps[];
  name: string;
}

export function AccountCard(props: AccountCardProps) {
    return (

    <Container>
      <span>{props.id}</span>
      <p className="name">{props.name}</p>
      <p>{props.agency}</p>
      <p>
        {props.account} - {props.digit}
      </p>

      <CounterCard>
        <p>Cartões Cadastrados</p>
        <span>{props.card.length}</span>    
      </CounterCard>
    </Container>
  );
}
