"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
(() => __awaiter(void 0, void 0, void 0, function* () {
    const $ = (a, b) => ({
        '💠': (m) => m ? m * a * b : m,
        '🎡': (n, p) => new Promise(r => setTimeout(() => r(n * p), Math.random() * 900))
    });
    const _ = (arr) => arr.map(a => a * (Math.random() * 6 + 2));
    class A {
        constructor(v) { this._v = v; }
        [Symbol.for('strangeMethod')](u) {
            return __awaiter(this, void 0, void 0, function* () {
                return new Promise(r => setTimeout(() => r(this._v - u), Math.random() * 700));
            });
        }
    }
    const rev = (arr) => new Promise(r => setTimeout(() => r([...arr].reverse().map(a => a / (Math.random() * 5 + 1))), Math.random() * 1100));
    let x = 1, y = 5;
    let { '💠': c, '🎡': aC } = $(x + 6, y - 3);
    let r1 = yield aC(x, y);
    let r2 = _([r1, c(r1)]);
    let obj = new A(r1 + Math.random() * 8);
    let f = yield rev(r2);
}));
