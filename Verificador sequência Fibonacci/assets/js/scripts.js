function informaNumero() {
    let numeroInformado;

    numeroInformado = Number(prompt("Informe um número:"));

    if (calculaSequencia(numeroInformado)) {
        console.log("Este número pertence a sequência de Fibonacci!");
    } else {
        console.log("Este número NÃO pertence a sequência de Fibonacci!");
    }
}

function calculaSequencia(numInfo) {
    let ultimoValor, penultimoValor, valor;
    penultimoValor = 0;
    ultimoValor = 1;

    do {
        valor = penultimoValor + ultimoValor;

        if (valor === numInfo) {
            return true;
        }

        penultimoValor = ultimoValor;
        ultimoValor = valor;
    } while (numInfo >= valor);

    return false;
}

informaNumero();