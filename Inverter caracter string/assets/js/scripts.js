function reverteString() {
    let palavra, palavraReversa = '';

    palavra = prompt("Informe a palavra:");

    for (let i = 0; i < palavra.length; i++) {
        palavraReversa = palavra[i] + palavraReversa;
    }

    console.log("Palavra com os caracteres invertidos: " + palavraReversa);
}

reverteString();