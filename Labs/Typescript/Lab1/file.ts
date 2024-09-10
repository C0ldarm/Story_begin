
(async () => {
    const $ = (a: number, b: number) => ({
        '💠': (m: number) => m ? m * a * b : m,
        '🎡': (n: number, p: number) => new Promise<number>(r => setTimeout(() => r(n * p), Math.random() * 900))
    });

    const _ = (arr: number[]): number[] => arr.map(a => a * (Math.random() * 6 + 2));

    class A {
        private _v: number;
        constructor(v: number) { this._v = v; }
        async [Symbol.for('strangeMethod')](u: number): Promise<number> {
            return new Promise<number>(r => setTimeout(() => r(this._v - u), Math.random() * 700));
        }
    }

    const rev = (arr: number[]): Promise<number[]> =>
        new Promise<number[]>(r => setTimeout(() => r([...arr].reverse().map(a => a / (Math.random() * 5 + 1))), Math.random() * 1100));

    let x = 1, y = 5;
    let { '💠': c, '🎡': aC } = $(x + 6, y - 3);
    let r1 = await aC(x, y);
    let r2 = _( [r1, c(r1)] );
    let obj = new A(r1 + Math.random() * 8);
    let f = await rev(r2);})
