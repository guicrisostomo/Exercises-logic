document.getElementById('buttonCheck').addEventListener('click', () => {
    let numInfo;
    let divYes = document.getElementById('divYes');
    let divNo = document.getElementById('divNo');

    numInfo = parseInt(document.getElementById('textNumber').value)

    if (calculaSequencia(numInfo)) {
        divYes.style.display = 'flex';
        divNo.style.display = 'none';
    } else {
        divYes.style.display = 'none';
        divNo.style.display = 'flex';
    }
    
    flashesDiv();
});

function calculaSequencia(numInfoParam) {
    if (!numInfoParam) return false;
    
    let lastValue, penultimateValue, value;
    penultimateValue = 0;
    lastValue = 1;

    do {
        value = penultimateValue + lastValue;

        if (value === numInfoParam) {
            return true;
        }

        penultimateValue = lastValue;
        lastValue = value;
    } while (numInfoParam >= value);

    return false;
}

function flashesDiv() {
    setInterval(function() {
        let divYes = document.getElementById('divYes');
        let divNo = document.getElementById('divNo');
    
        if (divYes.style.display === 'flex') {
            if (divYes.children[0].style.backgroundColor === 'green') {
                divYes.children[0].style.backgroundColor = '#023b02';
            } else {
                divYes.children[0].style.backgroundColor = 'green';
            }
        } else if (divNo.style.display === 'flex') {
            if (divNo.children[0].style.backgroundColor === 'red') {
                divNo.children[0].style.backgroundColor = '#840303';
            } else {
                divNo.children[0].style.backgroundColor = 'red';
            }
        }
    
    }, 1500);
}