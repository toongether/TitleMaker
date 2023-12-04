function main(title, fontPath, alignLeft) {
    try {
        var font = opentype.loadSync(fontPath);
    } catch (err) {
        print(err);
        return '';
    }

    var wrapText = function(text, width) {
        var regexString = '.{1,' + width + '}';
        var re = new RegExp(regexString, 'g');
        var lines = text.match(re) || [];
        var wrappedText = lines.map(function (line) {
            if (line.slice(-1) === '\n') {
                line = line.slice(0, line.length - 1);
            }
            return line.trim();
        });
        if (wrappedText.length > 3) return wrapText(text, width + 1);
        else return wrappedText;
    };

    var textArray = wrapText(title, 6);
    var maxWidth = font.getAdvanceWidth(
        textArray.reduce(function (a, b) {
            return a.length <= b.length ? b : a;
        }),
        100
    );

    var result = textArray
        .map(function (item, index) {
            var xPos = function () {
                if (alignLeft) return 0;
                else return (maxWidth - font.getAdvanceWidth(item, 100)) / 2;
            };
            var path = font.getPath(item, xPos(), 100 * (index + 1), 100);
            return path.toSVG();
        })
        .join("");

    return [
        '<?xml version="1.0"?><svg',
        'xmlns="http://www.w3.org/2000/svg"',
        'xmlns:xlink="http://www.w3.org/1999/xlink"',
        'fill="white" width="' + maxWidth + '" height="' + textArray.length * 110 + '"><g>' + result + '</g></svg>'
    ].join(" ");
}