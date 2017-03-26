/*
 * Copyright (c) 2017, Adam <Adam@sigterm.info>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.cache;

import java.io.File;
import java.io.IOException;
import net.runelite.cache.fs.Store;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Cache
{
	public static void main(String[] args) throws IOException
	{
		Options options = new Options();

		options.addOption("c", "cache", true, "cache base");
		options.addOption("t", "tree", true, "tree base");

		options.addOption("u", "unpack", false, "unpack cache");
		options.addOption("p", "pack", false, "pack cache");

		options.addOption(null, "items", true, "directory to dump items to");
		options.addOption(null, "npcs", true, "directory to dump npcs to");
		options.addOption(null, "objects", true, "directory to dump objects to");
		options.addOption(null, "sprites", true, "directory to dump sprites to");

		CommandLineParser parser = new DefaultParser();
		CommandLine cmd;
		try
		{
			cmd = parser.parse(options, args);
		}
		catch (ParseException ex)
		{
			System.err.println("Error parsing command line options: " + ex.getMessage());
			System.exit(-1);
			return;
		}

		String cache = cmd.getOptionValue("cache");
		String tree = cmd.getOptionValue("tree");

		if (cmd.hasOption("p"))
		{
			if (cache == null || tree == null)
			{
				System.err.println("Cache and tree base must be specified to pack");
				System.exit(-1);
			}

			System.out.print("Packing tree from " + tree + " to " + cache + "...");

			Store treeBase = new Store(new File(cache));
			treeBase.loadTree(new File(tree));
			treeBase.save();

			System.out.println(" done!");
			return;
		}
		else if (cmd.hasOption("u"))
		{
			if (cache == null || tree == null)
			{
				System.err.println("Cache and tree base must be specified to unpack");
				System.exit(-1);
			}

			System.out.print("Unpacking cache from " + cache + " to " + tree + "...");

			Store treeBase = new Store(new File(cache));
			treeBase.load();

			treeBase.saveTree(new File(tree));

			System.out.println(" done!");
			return;
		}

		if (cache != null && tree != null)
		{
			System.err.println("Cannot specify both cache and tree");
			System.exit(-1);
			return;
		}

		Store store = loadStore(cache, tree);

		if (cmd.hasOption("items"))
		{
			String itemdir = cmd.getOptionValue("items");

			if (itemdir == null)
			{
				System.err.println("Item directory must be specified");
				return;
			}

			System.out.println("Dumping items to " + itemdir);
			dumpItems(store, new File(itemdir));
		}
		else if (cmd.hasOption("npcs"))
		{
			String npcdir = cmd.getOptionValue("npcs");

			if (npcdir == null)
			{
				System.err.println("NPC directory must be specified");
				return;
			}

			System.out.println("Dumping npcs to " + npcdir);
			dumpNpcs(store, new File(npcdir));
		}
		else if (cmd.hasOption("objects"))
		{
			String objectdir = cmd.getOptionValue("objects");

			if (objectdir == null)
			{
				System.err.println("Object directory must be specified");
				return;
			}

			System.out.println("Dumping objects to " + objectdir);
			dumpObjects(store, new File(objectdir));
		}
		else if (cmd.hasOption("sprites"))
		{
			String spritedir = cmd.getOptionValue("sprites");

			if (spritedir == null)
			{
				System.err.println("Sprite directory must be specified");
				return;
			}

			System.out.println("Dumping sprites to " + spritedir);
			dumpSprites(store, new File(spritedir));
		}
		else
		{
			System.err.println("Nothing to do");
		}
	}

	private static Store loadStore(String cache, String tree) throws IOException
	{
		if (cache == null)
		{
			Store store = new Store(new File(tree));
			store.loadTree(new File(tree));
			return store;
		}
		else
		{
			Store store = new Store(new File(cache));
			store.load();
			return store;
		}

	}

	private static void dumpItems(Store store, File itemdir) throws IOException
	{
		ItemDumper dumper = new ItemDumper(store, itemdir, itemdir);
		dumper.load();
		dumper.dump();
		dumper.java();
	}

	private static void dumpNpcs(Store store, File npcdir) throws IOException
	{
		NpcDumper dumper = new NpcDumper(store, npcdir, npcdir);
		dumper.load();
		dumper.dump();
		dumper.java();
	}

	private static void dumpObjects(Store store, File objectdir) throws IOException
	{
		ObjectDumper dumper = new ObjectDumper(store, objectdir, objectdir);
		dumper.load();
		dumper.dump();
		dumper.java();
	}

	private static void dumpSprites(Store store, File spritedir) throws IOException
	{
		SpriteManager dumper = new SpriteManager(store);
		dumper.load();
		dumper.export(spritedir);
	}
}
