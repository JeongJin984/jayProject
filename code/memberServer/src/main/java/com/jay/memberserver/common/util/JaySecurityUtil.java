package com.jay.memberserver.common.util;

import com.auth0.jwt.algorithms.Algorithm;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.jcajce.provider.BouncyCastlePQCProvider;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.bouncycastle.util.io.pem.PemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Service
public class JaySecurityUtil {
    @Value("${security.rsa.publicKeyPath}")
    private String publicKeyPath;

    @Value("${security.rsa.privateKeyPath}")
    private String privateKeyPath;

    KeyFactory factory = KeyFactory.getInstance("RSA");

    public JaySecurityUtil() throws NoSuchAlgorithmException {
    }

    public Algorithm jwtSignAlgorithm(boolean write) throws NoSuchAlgorithmException, NoSuchProviderException, IOException, InvalidKeySpecException {
        KeyPair keyPair;
        if(write) {
            keyPair = writeRsaKeyPair();
        } else {
            keyPair = readRSAKeyPair();
        }
        return Algorithm.RSA256((RSAPublicKey) keyPair.getPublic(), (RSAPrivateKey) keyPair.getPrivate());
    }

    private KeyPair readRSAKeyPair() throws IOException, InvalidKeySpecException {
        PemReader publicKeyReader = new PemReader(new InputStreamReader(new FileInputStream(publicKeyPath)));
        PemReader privateKeyReader = new PemReader(new InputStreamReader(new FileInputStream(privateKeyPath)));


        RSAPublicKey rsaPublicKey =
                (RSAPublicKey) factory.generatePublic(
                        new X509EncodedKeySpec(publicKeyReader.readPemObject().getContent())
                );
        RSAPrivateKey rsaPrivateKey =
                (RSAPrivateKey) factory.generatePrivate(
                        new PKCS8EncodedKeySpec(privateKeyReader.readPemObject().getContent())
                );

        return new KeyPair(rsaPublicKey, rsaPrivateKey);
    }

    private KeyPair writeRsaKeyPair() throws NoSuchAlgorithmException, IOException {
        KeyPairGenerator generator =  KeyPairGenerator.getInstance("RSA", new BouncyCastleProvider());
        generator.initialize(1024);
        KeyPair keyPair = generator.generateKeyPair();

        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

        PemWriter publicKeyWriter = new PemWriter(new OutputStreamWriter(new FileOutputStream(publicKeyPath)));
        publicKeyWriter.writeObject(new PemObject("description", rsaPublicKey.getEncoded()));
        publicKeyWriter.flush();
        publicKeyWriter.close();

        PemWriter privateKeyWriter = new PemWriter(new OutputStreamWriter(new FileOutputStream(privateKeyPath)));
        privateKeyWriter.writeObject(new PemObject("description", rsaPrivateKey.getEncoded()));
        privateKeyWriter.flush();
        privateKeyWriter.close();
        return keyPair;
    }
}
