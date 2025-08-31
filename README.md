# Tetris - Projeto POO (2025.1 - T02)

[Link do repositório](https://github.com/VilefilipeDCOMP/Projeto-POO)

- Filipe Ciríaco Marcelino do Nascimento
- Luan Amaro dos Santos Aragão

## O que é exatamente?
Esse repositório apresenta um clone do jogo clássico Tetris, desenvolvido em Java e Python. O intuito desse projeto é aplicar os conhecimentos aprendidos na matéria de Programação Orientada a Objetos e visualizar sua aplicação na prática.

## Funcionalidades das duas versões

- Movimentação com o WASD ou as setas do teclado;
- Opção de rotacionar a peça;
- Sistema de verificação de colisão, tanto para locomoção e rotação das peças;
- Os 7 tetraminós presente no jogo de Alexey Pajitnov;
- Pontuação ao finalizar uma linha e opção de resetar;

## Como executar

- ### Java 

<code>java Java/Main.java</code>

- ### Python

<code>python Python/Main.py</code>

## Controles

- ↑ ou W: Rotacionar a peça atual;
- ← ou A: Movimentar o bloco para esquerda;
- → ou D: Movimentar o bloco para direita;
- ↓ ou S: Movimentar o bloco para baixo;

## Discussão sobre as duas linguagens

Antes de iniciar esse projeto, foi de extrema importância entender o que seria possível aplicar dentro do prazo. Essa precaução surgiu principalmente pela necessidade de utilizar uma segunda linguagem e ter que produzir dois aplicativos semelhantes, tanto na jogabilidade quanto nos gráficos apresentados ao usuário. 

Para a segunda linguagem, escolhemos Python pela semelhança e simples sintaxe da linguagem, porém, se preocupando ao máximo em aplicar o paradigma, a linguagem não se mostrou tão simples. O uso obrigatório do self tornou o código mais difícil de ler, tanto na utilização em funções como “self.atual.verificarXPos()” quanto a variáveis como a “self.atual.b[1].x”. 

Mesmo assim, o uso da orientação de objetos ocorreu de forma similar na linguagem Java, utilizado principalmente o polimorfismo de inclusão no aproveitamento de funções de uma superclasse para sua subclasse. Essa abordagem promoveu o reaproveitamento de código e a organização hierárquica das classes, resultando em um programa de fácil manutenção.

Apesar disso, o nosso maior problema foi fazer o visual, em que, acostumados a utilizar UI Builders para cada linguagem, ter que aplicar um método utilizando somente o desenho de formas geométricas pareceu ser impossível dentro do prazo. Essa adversidade quase fez nós desistirmos do projeto, porém, por incentivo do nosso professor, foi possível continuar no desenvolvimento e ter o resultado em que nós gostaríamos.
