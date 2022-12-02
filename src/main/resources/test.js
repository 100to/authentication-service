function rotateMatrix(matrix, rotations) {
    if (matrix.length === 0) return [];
    if (matrix[0].length === 0) return [];
    for (let j = 1; j <= rotations; j++) {
        let rotated90DegMatrix = [];
        let copyMatrix = [...matrix].reverse();
        for (let i = 0; i < matrix[0].length; i++) {
            newRow = [];
            if (j === rotations) {
                copyMatrix.map((x, index) => {
                    if (index === 0) newRow += x[i];
                    else newRow += `_${x[i]}`;
                });
            } else {
                newRow = copyMatrix.map((x) => {
                    return x[i];
                });
            }
            rotated90DegMatrix.push(newRow);
        }
        matrix = [...rotated90DegMatrix];
    }
    console.log(matrix);
}

rotateMatrix(
    [
        ["1", "2", "3","12"],
        ["4", "5", "6", "13"],
        ["7", "8", "9", "14"],
    ],
    3
);
