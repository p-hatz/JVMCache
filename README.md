## Overview
This JAR provides a JVM cache that is implemented using a HashMap. It can be used in Boomi as an alternative to using a cluster-wide Cache (e.g. Redis, Ehcache, Infinispan) and allows for the sharing of the Cache between different processes **on the same node**.

Perform the following to use in Boomi:
1. Upload [https://github.com/p-hatz/JVMCache/blob/main/NodeCache.jar](https://github.com/p-hatz/JVMCache/blob/main/NodeCache.jar) to your Boomi Account (Settings / Account Libraries)
2. Create a Custom Library of type **Scripting** and add the JAR in Step 1
3. Package and Deploy to your environment (you might need to bounce the runtime for the Library to be loaded)
4. You can now use in a Groovy script
5. (Optional): Install the Bundle at [https://platform.boomi.com/BoomiLabs.html#pub_bundles;/tab=my_bundles/bundle=9bb81af2-15eb-4e47-8c99-e73ee474b364/bundleOwner=true](https://platform.boomi.com/BoomiLabs.html#pub_bundles;/tab=my_bundles/bundle=9bb81af2-15eb-4e47-8c99-e73ee474b364/bundleOwner=true) that contains sample Add, Get, Delete and Clear cache processes.

## Design
This is a per JVM cache and so can easily be used in an Atom. When it comes to a Molecule, you would need to perform the Cache population across *all* Node JVMs (excluding Forked Execution JVMs). In a Molecule, the goal is to ensure that all Node JVMs have the latest Cache, though it's not guaranteed where a process will run (as it's up to the Head Node).
For this reason, the sample Population process checks for a Key in the Cache, and if it's not populated, it will populate using the data provided.
<p>
You could schedule the Cache Population process every hour for example and at some point, you would expect all nodes in the Cluster to have a populated Cache to be used by another process.
<p>
There is next to zero benefit in using this JAR on a Cloud Runtime as processes are executed in a Forked Execution (either an Atom Worker or a Runner), although you could technically call a sub-process to pre-populate a Cache for a Batch-style process (i.e. a process that runs in a Runner JVM), presuming the same Boomi process calls the sub-process.
