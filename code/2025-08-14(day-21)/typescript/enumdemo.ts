enum HttpStatusCode {
    OK = 200,
    NOTFOUND = 404,
    ACCESSDENIED = 403,
    INTERNALERROR = 500
}

console.log(HttpStatusCode.OK); 
console.log(HttpStatusCode[404]); 
console.log(HttpStatusCode.ACCESSDENIED);
