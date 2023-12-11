function main(title, alignLeft, wrap) {
    try {
        var font = opentype.loadSync(fontByteArray);
    } catch (err) {
        print(err);
        return '';
    }

    var wrapText = function(text, width) {
        var regexString = '.{1,' + width + '}';
        regexString += '([\\s\u200B]+|$)|[^\\s\u200B]+?([\\s\u200B]+|$)';
        var re = new RegExp(regexString, 'g');
        var lines = text.match(re) || [];
        var wrappedText = lines.map(function(line) {
            if (line.slice(-1) === '\n') {
                line = line.slice(0, line.length - 1);
            }
            return line.trim();
        });
        if (wrappedText.length > 3) return wrapText(text, width + 1);
        else return wrappedText;
    };

    var textArray = wrap ? wrapText(title, 6) : [title];
    var sizeArray = textArray.map(function(text) {
        return font.getAdvanceWidth(text, 100);
    });
    var maxWidth = Math.max.apply(null, sizeArray);

    var result = textArray
        .map(function (item, index) {
            var xPos = function () {
                if (alignLeft) return 0;
                else return (maxWidth - font.getAdvanceWidth(item, 100)) / 2;
            };
            var path = font.getPath(item, xPos(), 100 * (index + 1), 100);
            return path.toSVG();
        })
        .join('');

    return [
        '<?xml version="1.0"?><svg',
        'xmlns="http://www.w3.org/2000/svg"',
        'xmlns:xlink="http://www.w3.org/1999/xlink"',
        'fill="white" width="' + maxWidth + '" height="' + textArray.length * 110 + '"><g>' + result + '</g></svg>'
    ].join(' ');
}