from flask import Flask, render_template, request, stream_template
import zeep

app = Flask('CurrencyConverter', template_folder='templates')
client = zeep.Client(wsdl='http://localhost:8888/CurrencyConvert?WSDL')

answer = ''


@app.route('/', methods=['GET'])
def home():
    currencyList = client.service.getCurrencyList()
    return render_template("index.html", currencyList=currencyList, len=len(currencyList))


@app.route("/", methods=['POST'])
def currencyConvert():
    currency0 = request.form['currency1Name']
    currency1 = request.form['currency2Name']
    amount = request.form['value1']

    answer = str(client.service.convert(amount, currency0, currency1))
    currencyList = client.service.getCurrencyList()

    return render_template("CurrencyConvert.html", currencyList=currencyList, len=len(currencyList), answer=answer)


if __name__ == "__main__":
    app.run(debug=True)
