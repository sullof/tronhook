package org.tronhook.api.parser;


import org.tron.core.Wallet;
import org.tron.protos.Contract.TransferAssetContract;
import org.tron.protos.Contract.TransferContract;
import org.tron.protos.Protocol.Transaction.Contract;
import org.tronhook.api.model.contract.TransferAssetContractModel;
import org.tronhook.api.model.contract.TransferContractModel;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

public class ContractParser {

	
	public static Object parse(Contract contract) throws InvalidProtocolBufferException {
	
		ByteString contractByteString = contract.getParameter().getValue();
		
		switch (contract.getType()) {
		case TransferContract:
				return unpack(TransferContract.parseFrom(contractByteString));
		case TransferAssetContract:
			return unpack(TransferAssetContract.parseFrom(contractByteString));
		default:
			break;
		}
		
		
		
		return null;
	}

	private static TransferAssetContractModel unpack(TransferAssetContract parseFrom) {

		TransferAssetContractModel model = new TransferAssetContractModel();
		
		model.setAmount(parseFrom.getAmount());
		model.setFrom(Wallet.encode58Check(parseFrom.getOwnerAddress().toByteArray()));
		model.setTo(Wallet.encode58Check(parseFrom.getToAddress().toByteArray()));
		model.setAsset(parseFrom.getAssetName().toStringUtf8());

		
		return model;
	}

	private static TransferContractModel unpack(TransferContract parseFrom) {
		
		TransferContractModel model = new TransferContractModel();
		
		model.setAmount(parseFrom.getAmount());
		model.setFrom(Wallet.encode58Check(parseFrom.getOwnerAddress().toByteArray()));
		model.setTo(Wallet.encode58Check(parseFrom.getToAddress().toByteArray()));
		
		
		return model;
	}
	
	
	
	
}
