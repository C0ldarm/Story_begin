"""Бенчмарк V1 (Main.java з репо) vs V2 (оптимізована) на реальному harry.txt."""
import json
import os
import sys

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
import bench

CORPUS = "data/harry.txt"
bench.CORPORA["harry"] = CORPUS
VERS = ["V1", "V2"]
N = 15

res = {}
for ver in VERS:
    runs = [bench.run(bench.VERSIONS[ver], CORPUS) for _ in range(N)]
    assert all(r["ok"] for r in runs), runs
    res[ver] = {
        "internal_ms": bench.summarize(runs, "internal_ms"),
        "wall_ms": bench.summarize(runs, "wall_ms"),
        "rss_mb": bench.summarize(runs, "rss_mb"),
        "digest": runs[0]["digest"],
    }
    print(ver, json.dumps(res[ver]), flush=True)

print("outputs identical:", res["V1"]["digest"] == res["V2"]["digest"])

mh = {}
for ver in VERS:
    mh[ver] = bench.min_heap(ver, "harry", res[ver]["digest"])
    print(ver, "min -Xmx MB:", mh[ver], flush=True)

json.dump({"timing": res, "minheap": mh}, open(os.path.join(bench.WORK, "harry.json"), "w"), indent=1)
