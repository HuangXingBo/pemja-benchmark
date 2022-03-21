def json(s):
    import json
    a = json.loads(s)
    a['a'] = a['a'].lower()
    return json.dumps(a)
