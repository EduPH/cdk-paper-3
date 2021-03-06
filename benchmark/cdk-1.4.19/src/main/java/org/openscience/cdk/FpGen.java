/*
 * Copyright (c) 2017. NextMove Software Ltd.
 */

package org.openscience.cdk;

import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.fingerprint.Fingerprinter;
import org.openscience.cdk.fingerprint.IFingerprinter;
import org.openscience.cdk.fingerprint.MACCSFingerprinter;
import org.openscience.cdk.interfaces.IAtomContainer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.BitSet;
import java.util.Locale;

public final class FpGen extends CliExecutable {

  private final OptionSpec<String> typeSpec = optpar.accepts("type", "Fingerprint type, [circ, maccs, path]")
                                                    .withRequiredArg()
                                                    .ofType(String.class)
                                                    .defaultsTo("circ");

  IFingerprinter fpr;
  int num_bits;

  public FpGen()
  {
    super("fpgen");
  }

  @Override
  void configure(OptionSet optset, OutputStream out) throws IOException
  {
    final String fptype;

    final String fpkey = typeSpec.value(optset).toLowerCase(Locale.ROOT);
    switch (fpkey) {
      case "maccs":
      case "maccs166":
        fptype = "maccs166";
        fpr = new MACCSFingerprinter();
        break;
      case "path":
      case "daylight":
      case "dy":
        fptype = "path/length=7";
        fpr = new Fingerprinter(1024, 7);
        break;
      default:
        System.err.println("FATAL - Unknown fp type: " + fpkey);
        System.exit(1);
        return;
    }

    num_bits = fpr.getSize();

    out.write("#FPS1\n".getBytes(StandardCharsets.UTF_8));
    writeFpsHeader(out, "num_bits", Integer.toString(num_bits));
    writeFpsHeader(out, "toolkit", "CDK/2.0");
    writeFpsHeader(out, "type", fptype);
    writeFpsHeader(out, "date", getDateString());
    for (File file : optset.valuesOf(inputSpec))
      writeFpsHeader(out, "source", file.getAbsolutePath());
  }

  @Override
  boolean processMolecule(BufferedWriter out, IAtomContainer mol) throws CDKException, IOException
  {
    // API Change: IFingerprinter now returns either an IBitFingerprint or an ICountFingerprint
    BitSet fp = fpr.getFingerprint(mol);
    Object title = mol.getProperty(CDKConstants.TITLE);
    writeFps(out, fp, title == null ? "" : title.toString(), (int) Math.ceil(num_bits/64d) * 64);
    return true;
  }
}
