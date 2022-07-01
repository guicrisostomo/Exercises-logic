document.getElementById('buttonReverse').addEventListener('click', () => {
    let word, wordReverse = '';
    word = document.getElementById('textChar').value;
    
    if (!word) {
        document.getElementById('textResult').innerHTML = "Caracter inválido";
        return false;
    }

    for (let i = 0; i < word.length; i++) {
        wordReverse = word[i] + wordReverse;
    }

    document.getElementById('textResult').innerHTML = wordReverse;
});

document.getElementById('imgCopyText').addEventListener('click', () => {
    let word = document.getElementById('textResult').innerHTML;

    navigator.clipboard.writeText(word);

    alert('Texto copiado!');
});