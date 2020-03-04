<h1>Encryption Decryption</h1>
<h2>
 Parameters
</h2>
<ol type='1'>
  <li> -in -> input from a file<li>
  <li> -out -> Write encypted text to the file</li>
  <li> -alg -> type of algorithm (shift algorithm/ unicode algorithm)</li>
  <li> -data -> Input that is needed to be encryted</li>
  <li> -mode ->  Represents Encryption or Decryption<li>
  <li> -mode enc -> Encryption mode</li>
  <li> -mode dec ->  Decryption mode</li>
  <li> -key -> Secret Key for Caser Cypher Encryption </li>
</ol>
<h3>Any parameter can be ommited while giving the input command to the program<h3>
  <p>Example</p>
  <h4> java Main -data "Welcome" -key 5 -mode enc -alg shift -out protected.txt</h4>
  <p>
    Above command will Encrpyt the word "Welcome" with shift algorithm and store the encrypted version in protected.txt file
  </p>
