# wf-consul-util

## Bulk List

List all keys from Consul for a specified root directory.

	java -jar wf-util-consul-{version}.jar list root/
	
### Strip Root Directory

	java -jar wf-util-consul-{version}.jar list -strip root/

## Bulk Read

Reads all key/value pairs from Consul for a specified root directory.

	java -jar wf-util-consul-{version}.jar read root/
	
## Bulk Write

Writes all values from a properties file to Consul.

	java -jar wf-util-consul-{version}.jar write
	
## Consul Endpoint

Specify the environment variable `consul.endpoint` to set the target Consul Endpoint.

	java -Dconsul.endpoint=http://host:port -jar wf-util-consul-{version}.jar read root/
